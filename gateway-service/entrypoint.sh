while :
do
  status_code=$(curl --write-out %{http_code} --silent --output /dev/null http://eureka-service:8500)
  status_code_config=$(curl --write-out %{http_code} --silent --output /dev/null http://config-service:8888)

  echo "Eureka service response: $status_code\n"
  echo "Config service response: $status_code_config"

  if [ $status_code -eq 401 ] && [ $status_code_config -eq 200 ]
  then
    java -jar gateway.jar
    break
  fi

  sleep 3
done