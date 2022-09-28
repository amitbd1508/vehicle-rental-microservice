kubectl get pod  -l 'run=catalog' --no-headers | awk '{print $1}' | xargs -I {} sh -c 'echo {}; kubectl logs --follow {}'
