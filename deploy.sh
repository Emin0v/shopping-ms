case $1 in
  "build")

    echo "creating compiler container"

    docker build . -t microservices-compiler

    docker run -v $(pwd):/Users/Eminov/IdeaProjects/compiler/ microservices-compiler

    docker rmi microservices-compiler --force

    echo "compiled!"

  ;;
  "run")
    deployment-docker-compose -p "microservices" up -d --force-recreate --build
;;
esac