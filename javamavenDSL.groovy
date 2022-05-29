job('Java Maven APP DSL') {
  description('Java Maven con DSL para el curso de Jenkins')
  scm {
    git('https://github.com/macloujulian/simple-java-maven-app.git','master') { node ->
      node / gitConfigName('nicolasaceituno')
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
    shell('''
      echo "Entrega: Desplegando la aplicación" 
      java -jar "/var/jenkins_home/workspace/Java Maven APP DSL/target/my-app-1.0-SNAPSHOT.jar"
    ''')  
  }
  publishers {
    archiveArtifacts('target/*.jar')
    archiveJunit('target/surefire-reports/*.xml')
    mailer('nicolas.aceituno@gmail.com', true, true)
  }
}

    
