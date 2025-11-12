# ECR API (이미지 메타데이터)
resource "aws_vpc_endpoint" "ecr_api" {
    vpc_id              = var.vpc_id
    service_name        = "com.amazonaws.${var.region}.ecr.api"
    vpc_endpoint_type   = "Interface"
    private_dns_enabled = true
    subnet_ids          = var.private_subnet_ids
    security_group_ids  = [aws_security_group.vpce_sg.id]
}

# ECR DKR (이미지 레지스트리)
resource "aws_vpc_endpoint" "ecr_dkr" {
    vpc_id              = var.vpc_id
    service_name        = "com.amazonaws.${var.region}.ecr.dkr"
    vpc_endpoint_type   = "Interface"
    private_dns_enabled = true
    subnet_ids          = var.private_subnet_ids
    security_group_ids  = [aws_security_group.vpce_sg.id]
}

# CloudWatch Logs
resource "aws_vpc_endpoint" "logs" {
    vpc_id              = var.vpc_id
    service_name        = "com.amazonaws.${var.region}.logs"
    vpc_endpoint_type   = "Interface"
    private_dns_enabled = true
    subnet_ids          = var.private_subnet_ids
    security_group_ids  = [aws_security_group.vpce_sg.id]
}

# S3 (게이트웨이 엔드포인트) - 프라이빗 라우트 테이블에 연결
resource "aws_vpc_endpoint" "s3" {
    vpc_id            = var.vpc_id
    service_name      = "com.amazonaws.${var.region}.s3"
    vpc_endpoint_type = "Gateway"
    route_table_ids = [var.private_route_table_id]
}