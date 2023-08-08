# Getting Started

Simple project to show how to
create [sidecar pattern](https://learn.microsoft.com/en-us/azure/architecture/patterns/sidecar).
It represents nginx proxy that will intercept calls and send it to the authorisation server, authorisation server
responds with 200 or 401 and based on that the request is sent to the main-app. Very simple logic is, first nginx itself
checks if header is present if not, then rejects the request, otherwise authorisation server simulates rejecting the
authorisation when token is contains 'tokenXXXXX'.

### Configuration

The nginx configuration consist of two .conf file

* nginx-main.conf

This file is necessary to enable JavaScript code execution within NGINX. It contains
the [ngx_http_js_module](http://nginx.org/en/docs/http/ngx_http_js_module.html) that needs
to be located at in the top-level ("main") context of NGINX. It allows us to write a bit of JS code to enhance
functionality of nginx.

* nginx-server.conf

This is the primary configuration file for the NGINX server, allowing the interception of requests. It utilizes the
[auth_request module](http://nginx.org/en/docs/http/ngx_http_auth_request_module.html), an integral part of NGINX,
responsible for handling the interception and authentication functionality.

* auth2.js

This JavaScript file performs the simple validation logic for the Authorization header and passes request to the
authorisation-server.

* Dockerfile

The Dockerfile contains the instructions to build the entire project into a reusable Docker image. It installs the
necessary dependencies, copies the configuration files, and sets up the NGINX server.

### How to Use

Follow the instructions below to set up and run the project.

#### Prerequisite

* having minikube env set up
* having docker env set up
* java17

####

Run following command

1. **Build docker images**
   `cd main-app && mvn clean package && docker build . --tag=main-app && cd ../authorisation-server && mvn clean package && docker
   build . --tag=authorisation-server && cd .. && docker build . --tag=custom-nginx && docker images`

2. **Start Minikube**: Simply run the following command:
   `minikube start`

3. **Configure Minikube to Use Local Repository.** Instead of the Docker Registry, execute:
   `eval $(minikube docker-env)`

4. **Simplify Executing kubectl Commands** Set up the namespace and apply the required configurations:
   `kubectl config set-context --current --namespace=sidecar-namespace
   kubectl apply -f sidecar-deployment.yaml
   kubectl apply -f sidecar-service.yaml
   kubectl apply -f sidecar-namespace.yaml`

5. **Expose the Service Outside of the Cluster Network**
   `minikube service sidecar-service --url`

6. **Access the Application**. In your browser, you can access the application at the following URLs, replacing
   MINIKUBE_IP with the IP address provided by Minikube:
   http://MINIKUBE_IP:30010/sampleTest`