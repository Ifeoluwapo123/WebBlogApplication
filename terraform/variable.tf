variable "aws_region" {
  type        = string
  description = "VPC Region"
  default     = "us-east-1"
}

variable "aws_access_key" {
  type        = string
  description = "AWS access key"
}

variable "aws_secret_key" {
  type        = string
  description = "AWS secret key"
}

variable "environment_suffix" {
  type    = string
  default = "production"
}

variable "myapp_version" {
  type    = string
  default = "1.0.0"
}
