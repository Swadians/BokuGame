import requests
try:
    r = requests.get("http://192.168.0.106:8080/movimentos")
    resposta = eval(r.content)

    print(resposta)
    # prints the int of the status code. Find more at httpstatusrappers.com :)
except requests.ConnectionError:
    print("failed to connect")


