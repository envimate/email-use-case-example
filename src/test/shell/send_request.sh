#!/usr/bin/env bash

script_path="$( cd "$(dirname "$0")" ; pwd -P )"

#echo "@${script_path}/../request.json"
curl -i -X POST \
    -H "Content-Type: application/json" \
     http://localhost:1337/api/sendEmail \
      --data "@${script_path}/../request.json"
