terraform {
    required_providers {
        aws = {
            source  = "hashicorp/aws"
            version = "~> 4.16"
        }
    }

    required_version = ">= 1.2.0"
}

provider "aws" {
    region  = var.region
    access_key = var.access_key
    secret_key = var.secret_key
}

module "iam" {
    source = "./IAM"
}

module "s3" {
    source = "./S3"
    depends_on = [module.iam]
}

module "attach_policy" {
    source = "./Attach_Policy"
    depends_on = [module.s3, module.iam]
}

locals {
    access_key_id = module.iam.access_key_id
    secret_access_key = module.iam.secret_access_key
    bucket_name = module.s3.bucket_name
}

resource "local_file" "update_credentials_file" {
    filename = "../src/main/resources/application.properties"
    content  = "${file("../src/main/resources/application.properties")}\n# AWS credentials of the user s3_user_spring_boot_jupiter. Generate from terraform script. \naws.S3.properties.accessKey=${local.access_key_id}\naws.S3.properties.secretKey=${local.secret_access_key}\naws.S3.properties.region=${var.region}\naws.S3.properties.bucketName=${local.bucket_name}\n"
}

