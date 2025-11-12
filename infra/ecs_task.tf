resource "aws_ecs_task_definition" "api" {
    family                   = "portfolio-api-td"
    network_mode             = "awsvpc"
    requires_compatibilities = ["FARGATE"]
    cpu                      = var.task_cpu
    memory                   = var.task_memory
    execution_role_arn       = aws_iam_role.task_exec_role.arn

    container_definitions = jsonencode([
        {
            name      = "api"
            image     = var.ecr_image_uri
            essential = true
            portMappings = [{
                containerPort = var.container_port
                protocol      = "tcp"
            }]
            environment = [
                { name = "SPRING_PROFILES_ACTIVE", value = "prod" },
                { name = "DB_HOST",     value = var.db_host },
                { name = "DB_PORT",     value = tostring(var.db_port) },
                { name = "DB_NAME",     value = var.db_name },
                { name = "DB_USERNAME", value = var.db_username },
                { name = "DB_PASSWORD", value = var.db_password }
            ]
            logConfiguration = {
                logDriver = "awslogs"
                options = {
                awslogs-group         = aws_cloudwatch_log_group.api.name
                awslogs-region        = var.region
                awslogs-stream-prefix = "ecs"
                }
            }
            healthCheck = {
                command     = ["CMD-SHELL", "curl -f http://localhost:${var.container_port}${var.health_check_path} || exit 1"]
                interval    = 30
                timeout     = 5
                retries     = 3
                startPeriod = 20
            }
        }
    ])
}