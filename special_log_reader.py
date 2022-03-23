import os
user_input = input("please type the name of the file from the root directory you would like to read:")

if user_input == 'flag.txt' or 'logs.txt' not in user_input:
	print("You are only allowed to read the log files! We talked about this!")
	exit()

bad_inputs = ["'",'_','"',',','\\','<','>','/','&','|','-','/']
for n in bad_inputs:
	if n in user_input:
		print('Bad input detected!')
		exit()

#This is the best way to read files in python right? 
os.system('cat /root/' + user_input)

