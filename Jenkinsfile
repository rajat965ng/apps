pipeline {
    agent none
    stages {
        stage('Development Deploy') {
            agent any
            steps {
                wrap([$class: 'BuildUser']) {
                   echo "userId=${BUILD_USER_ID},fullName=${BUILD_USER},email=${BUILD_USER_EMAIL}"
                }
                echo "${params.version} the version"
                sh script: 'kubectl get nodes', label: 'Configure Namespace'
                sh script: 'sed -i  s/tag_version/`openssl rand -hex 3`/g dev/deployment.yaml'
                sh script: 'kubectl apply -f dev/namespace.yaml', label: 'Configure Namespace'
                sh script: 'kubectl apply -f dev/config.yaml', label: 'Deploy ConfigMap and Secrets'
                sh script: 'kubectl apply -f dev/deployment.yaml', label: 'Execute Deployment'
            }
        }
    }
}