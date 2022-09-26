mvn clean package  -DskipTests -X
kubectl config use-context minikube
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.6.1/aio/deploy/recommended.yaml
skaffold dev
