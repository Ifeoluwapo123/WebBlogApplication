resource "aws_s3_bucket" "s3_bucket_myapp" {
  bucket = "blogapi-adenusidamilola5gmailcom-${var.environment_suffix}"
  acl    = "private"

  tags = {
    Name        = "Blog"
    Environment = "Prod"
  }
}

resource "aws_s3_bucket_object" "s3_bucket_object_myapp" {
  bucket = aws_s3_bucket.s3_bucket_myapp.id
  key    = "beanstalk/myapp"
  source = "../target/WebBlog-0.0.1-SNAPSHOT.jar"
}

resource "aws_elastic_beanstalk_application" "beanstalk_myapp" {
  name        = "myapp"
  description = "The description of my application"
}

resource "aws_elastic_beanstalk_application_version" "beanstalk_myapp_version" {
  application = aws_elastic_beanstalk_application.beanstalk_myapp.name
  bucket      = aws_s3_bucket.s3_bucket_myapp.id
  key         = aws_s3_bucket_object.s3_bucket_object_myapp.id
  name        = "myapp-${var.myapp_version}"
}

resource "aws_elastic_beanstalk_environment" "springboot_app" {
  name                = "myapp-prod"
  application         = aws_elastic_beanstalk_application.beanstalk_myapp.name
  solution_stack_name = "64bit Amazon Linux 2 v3.2.10 running Corretto 11"
  version_label       = aws_elastic_beanstalk_application_version.beanstalk_myapp_version.name

  setting {
    namespace = "aws:ec2:vpc"
    name      = "VPCId"
    value     = aws_vpc.main.id
  }

  setting {
    namespace = "aws:ec2:vpc"
    name      = "Subnets"
    value     = "${aws_subnet.main-private-1.id},${aws_subnet.main-private-2.id}"
  }

  setting {
    namespace = "aws:ec2:vpc"
    name      = "AssociatePublicIpAddress"
    value     = "false"
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "IamInstanceProfile"
    value     = aws_iam_instance_profile.app-ec2-role.name
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "SecurityGroups"
    value     = aws_security_group.app-prod.id
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "EC2KeyName"
    value     = aws_key_pair.mykeypair.id
  }

  setting {
    namespace = "aws:ec2:instances"
    name      = "InstanceTypes"
    value     = "t2.micro"
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "IamInstanceProfile"
    value     = "aws-elasticbeanstalk-ec2-role"
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "InstanceType"
    value     = "t2.micro"
  }
  setting {
    namespace = "aws:elasticbeanstalk:environment"
    name      = "ServiceRole"
    value     = aws_iam_role.elasticbeanstalk-service-role.name
  }
  setting {
    namespace = "aws:ec2:vpc"
    name      = "ELBScheme"
    value     = "public"
  }
  setting {
    namespace = "aws:ec2:vpc"
    name      = "ELBSubnets"
    value     = "${aws_subnet.main-public-1.id},${aws_subnet.main-public-2.id}"
  }
  setting {
    namespace = "aws:elb:loadbalancer"
    name      = "CrossZone"
    value     = "true"
  }
  setting {
    namespace = "aws:elasticbeanstalk:command"
    name      = "BatchSize"
    value     = "30"
  }
  setting {
    namespace = "aws:elasticbeanstalk:command"
    name      = "BatchSizeType"
    value     = "Percentage"
  }
  setting {
    namespace = "aws:autoscaling:asg"
    name      = "Availability Zones"
    value     = "Any 2"
  }
  setting {
    namespace = "aws:autoscaling:asg"
    name      = "MinSize"
    value     = "1"
  }
  setting {
    namespace = "aws:autoscaling:updatepolicy:rollingupdate"
    name      = "RollingUpdateType"
    value     = "Health"
  }

  setting {
    name      = "SERVER_PORT"
    namespace = "aws:elasticbeanstalk:application:environment"
    value     = "5000"
  }

}