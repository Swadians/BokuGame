# -*- coding: utf-8 -*-
import copy
import os
import threading
import multiprocessing
from sys import argv
from flask import Flask, render_template, request, jsonify
from flask_classy import FlaskView, route
from flask_socketio import SocketIO, emit

def threaded_function(portNumber):
	os.system("paralel.py " + portNumber)


file = open("Ports", "w")
for x in range(0, multiprocessing.cpu_count()):
	portNumber = argv[1] + str(x)
	
	file.write(portNumber)
	file.write("\n")
	threading.Thread(target = threaded_function, args = (portNumber, )).start()
file.close()		

    





