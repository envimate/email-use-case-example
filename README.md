# HttpMate and MapMate showcase

This is an example project for (HttpMate)[https://github.com/envimate/httpmate] and (MapMate)[https://github.com/envimate/mapmate] opensource projects. It shows the usage of HttpMate with different configurations in the so-called UseCase mode. 
The bases for this example is a simple Email sending UseCase. 

## Usage

Checkout (this blog post)[https://blog.envimate.com/httpmate-intro] to find description of this project.

You can easily run the example by 

1. checking it out to your local

```bash

git checkout git@github.com:envimate/email-use-case-example.git

```

2. Running the `com.envimate.examples.email_use_case.Application`

This will start an http server, serving the path `/api/sendEmail`

For details on how this endpoint is configured, checkout the file `com.envimate.examples.email_use_case.infrastructure.http.HttpMateFactory`

3. To see some sample requests, execute the script `src/test/shell/send_request.sh`. It send 2 requests - a valid request
and a request containing invalid sender and receiver addresses. Note that the response contains validation errors for both
fields.
