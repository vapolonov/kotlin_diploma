pipeline {
  agent any

  parameters {
    choice(
      name: 'TEST_TYPE',
      choices: ['ui', 'api', 'all'],
      description: 'Тип тестов'
    )
  }

  environment {
    IMAGE_NAME = 'playwright-tests'
  }

  stages {

    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build Test Image') {
      steps {
//         sh 'docker build -t $IMAGE_NAME -f docker/tests/Dockerfile .'
        sh 'docker build -t $IMAGE_NAME -f Dockerfile .'
      }
    }

    stage('Run Tests') {
      steps {
        script {

          def cmd = params.TEST_TYPE == 'ui' ? 'ui_test' :
                    params.TEST_TYPE == 'api' ? 'api_test' :
                    'ui_test api_test'

          sh """
          docker run --rm \
            -v \$PWD/build:/app/build \
            -v \$HOME/.gradle:/home/gradle/.gradle \
            $IMAGE_NAME ./gradlew clean ${cmd} allureReport --no-daemon
          """
        }
      }
    }
  }

  post {
    always {
      archiveArtifacts artifacts: 'build/allure-results/**', allowEmptyArchive: true

      allure([
        results: [[path: 'build/allure-results']]
      ])
    }
  }
}