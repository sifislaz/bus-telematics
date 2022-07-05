# Bus Telematics API

## Installation Guide
The instructions are suitable for installation in linux based machine. In case you don't have a native linux installation, we recommend you to create a virtual machine with Ubuntu. In that case, don't forget to forward a port from your VM manager to your host machine, in order to access the API from your browser.

1. Download and install _Docker_ to your machine, following the [instructions](https://docs.docker.com/engine/install/ubuntu/ "Docker Installation for Ubuntu")
2. In a terminal, run the command `sudo docker pull sifislaz/bustelematics:0.0.1`
3. Run the command `sudo docker run -d -p [EXPOSED_PORT]:8888 sifislaz/bustelematics:0.0.1`, where EXPOSED_PORT is the port that you have forwarded to your host machine.
4. From your browser, go to `localhost:[EXPOSED_PORT]/swagger-ui/`
5. (Optional) If you want to watch the logs of the server, as well as the simulation process, you can run the commands:
    1. `sudo docker images`, from which you can obtain the id of the running container
    2. `sudo docker logs -f [CONTAINER_ID]`, which will return the log of the running container.