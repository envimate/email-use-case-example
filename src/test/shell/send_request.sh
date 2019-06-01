#!/usr/bin/env bash

script_path="$( cd "$(dirname "$0")" ; pwd -P )"
#
#curl -i -X POST \
#    -H "Content-Type: application/json" \
#     http://localhost:1337/api/sendEmail \
#      --data "@${script_path}/../request.json"
#
#curl -i -X POST \
#    -H "Content-Type: application/json" \
#     http://localhost:1337/3/api/sendEmail \
#      --data "@${script_path}/../request.json"
#

curl -i -X POST -H "Version: v3" \
    -H "Content-Type: application/json" \
     http://localhost:1337/api/sendEmail \
      --data "@${script_path}/../request.json"

#curl -i "http://localhost:1337/api/sendEmail?sender=sender@example.com&receiver=receiver@example.com&subject=subject&body=HelloWorld"
#
#curl -i "http://localhost:1337/api/sendEmailWithPathParameters/sender@example.com/receiver@example.com/subject/HelloWorld"
