variable "region" {
    type        = string
    description = "AWS region (기본: 서울)"
    default     = "ap-northeast-2"
}

variable "vpc_id" {
    type        = string
    description = "기존 VPC ID"
}

variable "private_subnet_ids" {
    type        = list(string)
    description = "프라이빗 서브넷 2개 이상"
}

variable "public_subnet_ids" {
    type        = list(string)
    description = "퍼블릭 서브넷 2개 이상"
}

variable "container_port" {
    type    = number 
    default = 8080 
}

variable "health_check_path" {
    type    = string 
    default = "/_health" 
}

variable "ecr_image_uri" {
    type = string
}

variable "task_cpu" {
    type    = string
    default = "512" 
}

variable "task_memory" {
    type    = string
    default = "1024"
}

variable "desired_count" {
    type    = number
    default = 1
}

# DB 연결
variable "db_host" {
    type = string
}

variable "db_port" {
    type    = number
    default = 3306
}

variable "db_name" {
    type = string
}

variable "db_username" {
    type = string
}

variable "db_password" {
    type = string
    sensitive = true
}

variable "private_route_table_id" {
    type        = string
    description = "프라이빗 서브넷에 연결된 라우트 테이블 ID"
}

variable "db_security_group_id" {
    type        = string
    description = "RDS security group ID"
}

variable "acm_cert_arn" {
    description = "ACM certificate ARN for api.uniquehan.com"
    type        = string
}