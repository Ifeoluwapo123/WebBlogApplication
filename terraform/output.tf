output "eb" {
  value = aws_elastic_beanstalk_environment.springboot_app.cname
}

output "eb-api-swagger" {
  value = "${aws_elastic_beanstalk_environment.springboot_app.cname}/swagger-ui/#/"
}
