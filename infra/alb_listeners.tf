# HTTP :80 → HTTPS :443 리다이렉트
resource "aws_lb_listener" "http" {
    load_balancer_arn = aws_lb.alb.arn
    port              = 80
    protocol          = "HTTP"

    default_action {
        type = "redirect"
        redirect {
            port        = "443"
            protocol    = "HTTPS"
            status_code = "HTTP_301"
        }
    }
}

# HTTPS :443 → TG 포워드
resource "aws_lb_listener" "https" {
    load_balancer_arn = aws_lb.alb.arn
    port              = 443
    protocol          = "HTTPS"
    ssl_policy        = "ELBSecurityPolicy-TLS13-1-2-Res-2021-06"
    certificate_arn   = var.acm_cert_arn

    default_action {
        type             = "forward"
        target_group_arn = aws_lb_target_group.tg.arn
    }
}