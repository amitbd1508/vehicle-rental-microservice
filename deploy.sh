kubectl config set-context gke_possible-tape-183617_us-central1-a_neg-demo-cluster
gcloud auth activate-service-account  --key-file=possible-tape-183617-88202a5a6565.json
gcloud config set account  amitwork2017@gmail.com
gcloud config set project possible-tape-183617

mvn clean package  -DskipTests -X
skaffold build
skaffold run


export GITHUB_REPO=amitbd1508/vehicle-rental-microservice
export PROJECT_ID=possible-tape-183617
export SERVICE_ACCOUNT=github-actions-vehicle-rental
export WORKLOAD_IDENTITY_POOL=gh-pool
export WORKLOAD_IDENTITY_PROVIDER=gh-provider

echo $WORKLOAD_IDENTITY_PROVIDER_LOCATION
projects/222904314823/locations/global/workloadIdentityPools/gh-pool/providers/gh-provider
echo $SERVICE_ACCOUNT@$PROJECT_ID.iam.gserviceaccount.com
github-actions-vehicle-rental@possible-tape-183617.iam.gserviceaccount.com
https://blog.leandrotoledo.org/deploying-google-cloud-functions-using-github-actions-and-workload-identity-authentication/
