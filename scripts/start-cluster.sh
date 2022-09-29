set -e
set -x

minikube start --vm-driver=virtualbox --memory='9000mb'
eval $(minikube docker-env)
kubectl config set-context minikube
minikube addons enable metrics-server
