kubectl get pod  -l 'run=search' --no-headers | awk '{print $1}' | xargs -I {} sh -c 'echo {}; kubectl logs --follow {}'
