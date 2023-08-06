# Getting Started

Simple project to show how to
create [sidecar pattern](https://learn.microsoft.com/en-us/azure/architecture/patterns/sidecar).
For simplicity both sidecar and main app are in the same repo, however sidecar app can be easily stored as standalone
project and being pluggable to various application as long as functionality of sidecar matches the expectation of the
main
app.

### Configuration

By setting the Kubernetes property (ROUTING_URL) in the sidecar-deployment.yaml, we define the url that sidecar-app can
route request to
which makes the sidecar application reusable.

### Example and Extensibility

That particular example is only hello-world like, however it's very easy to add functionality of authorization against
some other service and only allow to pass request to the main app when authorization logic is met.

### How to Use

Follow the instructions below to set up and run the project.

#### Prerequisite

* having minikube env set up
* having docker env set up
* java17

####

Run following command

1. **Build docker images**
   `cd main-app && mvn clean package && docker build . --tag=main-app && cd ../sidecar-app && mvn clean package && docker
   build . --tag=sidecar-app && cd .. && docker images`

2. **Start Minikube**: Simply run the following command:
   `minikube start`

3. **Configure Minikube to Use Local Repository.** Instead of the Docker Registry, execute:
   `eval $(minikube docker-env)`

4. **Simplify Executing kubectl Commands** Set up the namespace and apply the required configurations:
   kubectl config set-context --current --namespace=sidecar-namespace
   kubectl apply -f sidecar-deployment.yaml
   kubectl apply -f sidecar-service.yaml
   kubectl apply -f sidecar-namespace.yaml

5. **Expose the Service Outside of the Cluster Network**
minikube service sidecar-service --url

6. **Access the Application**. In your browser, you can access the application at the following URLs, replacing MINIKUBE_IP with the IP address provided by Minikube:
http://MINIKUBE_IP:30010/sidecar
http://MINIKUBE_IP:30005/sampleTest