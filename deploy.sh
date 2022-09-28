kubectl config set-context gke_possible-tape-183617_us-central1-a_neg-demo-cluster
gcloud auth activate-service-account  --key-file=possible-tape-183617-88202a5a6565.json
gcloud config set account  amitwork2017@gmail.com
gcloud config set project possible-tape-183617

mvn clean package  -DskipTests -X
skaffold build
skaffold run
