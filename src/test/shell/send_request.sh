#!/usr/bin/env bash

script_path="$( cd "$(dirname "$0")" ; pwd -P )"

printf "\n===========================Sending a valid request===========================\n"

curl -i -X POST \
    -H "Content-Type: application/json" \
     http://localhost:1337/api/sendEmail \
      --data "@${script_path}/../data/request.json"

printf "\n===========================Sending a valid request done======================\n"

printf "\n===========================Sending an invalid request...=====================\n"

curl -i -X POST \
    -H "Content-Type: application/json" \
     http://localhost:1337/api/sendEmail \
      --data "@${script_path}/../data/request_invalid.json"

printf "\n===========================Sending an invalid request done====================\n"
