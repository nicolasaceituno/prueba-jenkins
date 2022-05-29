job('Java Maven APP DSL') {
  description('Java Maven con DSL para el curso de Jenkins')
  scm {
    git('https://github.com/macloujulian/simple-java-maven-app','master') { node ->
      node / gitConfigName('Nicolas')
      node / gitConfigEmail('nicolas.aceituno@gmail.com')
    }
  }
  steps {
    maven {
      mavenInstallation('mavenjenkins')
      goals('-B -DskipTests clean package')
    }
    maven {
      mavenInstallation('mavenjenkins')
      goals('test')
    }
    shell {'''
      echo "Entrega: Desplegando la aplicacion"
      java -jar "/var/jenkins_home/workspace/Java Maven App DSL/target/my-app-1.0-SNAPSHOT.jar"
    '''}
    publishers {
      archiveArtifact('target/*.jar')
      archiveJunit('target/surfire-reports/*.xml')
      mailer('nicolas.aceituno@gmail.com', true, true)
    }
  }
}

    
