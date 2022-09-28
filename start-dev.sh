mvn clean package  -DskipTests -X
skaffold delete && skaffold build && skaffold run
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.6.1/aio/deploy/recommended.yaml
skaffold dev
