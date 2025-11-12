resource "aws_cloudwatch_log_group" "api" {
    name              = "/ecs/portfolio-api"
    retention_in_days = 14
}