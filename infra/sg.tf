# ALB가 붙을 SG (퍼블릭 80 허용)
resource "aws_security_group" "alb_sg" {
    name        = "portfolio-alb-sg"
    description = "ALB security group"
    vpc_id      = var.vpc_id

    egress {
        from_port   = 0
        to_port     = 0
        protocol    = "-1"
        cidr_blocks = ["0.0.0.0/0"]
        ipv6_cidr_blocks = ["::/0"]
    }
}

# HTTP 80
resource "aws_security_group_rule" "alb_ingress_http_ipv4" {
    type              = "ingress"
    from_port         = 80
    to_port           = 80
    protocol          = "tcp"
    cidr_blocks       = ["0.0.0.0/0"]
    security_group_id = aws_security_group.alb_sg.id
    description       = "HTTP from anywhere"
}

resource "aws_security_group_rule" "alb_ingress_http_ipv6" {
    type                 = "ingress"
    from_port            = 80
    to_port              = 80
    protocol             = "tcp"
    ipv6_cidr_blocks     = ["::/0"]
    security_group_id    = aws_security_group.alb_sg.id
    description          = "HTTP from anywhere (IPv6)"
}

# HTTPS 443
resource "aws_security_group_rule" "alb_ingress_https_ipv4" {
    type              = "ingress"
    from_port         = 443
    to_port           = 443
    protocol          = "tcp"
    cidr_blocks       = ["0.0.0.0/0"]
    security_group_id = aws_security_group.alb_sg.id
    description       = "HTTPS from anywhere"
}
resource "aws_security_group_rule" "alb_ingress_https_ipv6" {
    type                 = "ingress"
    from_port            = 443
    to_port              = 443
    protocol             = "tcp"
    ipv6_cidr_blocks     = ["::/0"]
    security_group_id    = aws_security_group.alb_sg.id
    description          = "HTTPS from anywhere (IPv6)"
}

# ECS 태스크가 붙을 SG (ALB에서만 8080 허용)
resource "aws_security_group" "ecs_sg" {
    name        = "portfolio-ecs-sg"
    description = "ECS tasks security group"
    vpc_id      = var.vpc_id

    ingress {
        description     = "ALB to ECS 8080"
        from_port       = 8080
        to_port         = 8080
        protocol        = "tcp"
        security_groups = [aws_security_group.alb_sg.id]
    }

    # 초기엔 전체 아웃바운드 허용(추후 RDS 등으로 좁힐 수 있음)
    egress {
        from_port   = 0
        to_port     = 0
        protocol    = "-1"
        cidr_blocks = ["0.0.0.0/0"]
    }
}

# VPC Endpoint 전용 SG: 443을 ECS SG에서만 허용
resource "aws_security_group" "vpce_sg" {
    name        = "portfolio-vpce-sg"
    description = "Security group for VPC interface endpoints (ECR/Logs)"
    vpc_id      = var.vpc_id

    # ECS 태스크(ecs_sg)로부터의 HTTPS(443)만 허용
    ingress {
        description     = "HTTPS from ECS tasks"
        from_port       = 443
        to_port         = 443
        protocol        = "tcp"
        security_groups = [aws_security_group.ecs_sg.id]
    }

    # 엔드포인트 ENI에서 나가는 응답 트래픽 허용
    egress {
        from_port   = 0
        to_port     = 0
        protocol    = "-1"
        cidr_blocks = ["0.0.0.0/0"]
    }
}

# ECS → RDS 3306 허용
resource "aws_security_group_rule" "db_ingress_from_ecs" {
    type                     = "ingress"
    from_port                = 3306
    to_port                  = 3306
    protocol                 = "tcp"
    security_group_id        = var.db_security_group_id
    source_security_group_id = aws_security_group.ecs_sg.id
    description              = "Allow ECS tasks to access RDS"
}