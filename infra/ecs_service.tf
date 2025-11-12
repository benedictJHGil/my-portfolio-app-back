resource "aws_ecs_service" "api" {
    name            = "portfolio-api-svc"
    cluster         = aws_ecs_cluster.this.id
    task_definition = aws_ecs_task_definition.api.arn
    desired_count   = var.desired_count
    launch_type     = "FARGATE"

    network_configuration {
        assign_public_ip = false
        subnets          = var.private_subnet_ids
        security_groups  = [aws_security_group.ecs_sg.id]
    }

    load_balancer {
        target_group_arn = aws_lb_target_group.tg.arn
        container_name   = "api"
        container_port   = var.container_port
    }

    depends_on = [
        aws_lb_listener.http,
        aws_vpc_endpoint.ecr_api,
        aws_vpc_endpoint.ecr_dkr,
        aws_vpc_endpoint.logs
    ]

    force_new_deployment = true
}