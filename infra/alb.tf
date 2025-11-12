# 퍼블릭 ALB
resource "aws_lb" "alb" {
    name               = "portfolio-alb"
    load_balancer_type = "application"
    security_groups    = [aws_security_group.alb_sg.id]
    subnets            = var.public_subnet_ids
}

# ECS(Fargate)가 붙을 타깃 그룹 (IP 타입)
resource "aws_lb_target_group" "tg" {
    name        = "portfolio-tg"
    port        = var.container_port
    protocol    = "HTTP"
    target_type = "ip"
    vpc_id      = var.vpc_id

    health_check {
        path                = var.health_check_path
        interval            = 30
        timeout             = 5
        healthy_threshold   = 2
        unhealthy_threshold = 3
        matcher             = "200-399"
    }
}