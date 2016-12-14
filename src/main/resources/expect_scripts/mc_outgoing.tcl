#!/usr/bin/expect -f

set ave_runtime_sec 250
set user prduc
set server 10.133.179.201
set password cms@123
set port 22

spawn ssh -p $port $user@$server

expect "password$"
send -- "$password\r"

expect ".*\$"
send -- "menu\r"

expect -exact "Enter Option (or Q to Quit):"
send -- "2\r"

expect -exact "Enter Option (or Q to Quit):"
send -- "1\r"

expect -exact "Please enter the number of the Batch Job to process, e\[X\]it:"
send -- "5\r"

expect -exact "Please type OK <enter> to proceed"
send -- "OK\r"

set timeout $ave_runtime_sec

expect {

	"Cadencie_ProcessJob: Cannot process job: recovery required on previous run" {

		send_user "Failed processing Mastercard Outgoing. Recovery required on previous run.\n"
		exit 1;
	}

	"Run R111 editor? Please type OK <enter> to proceed" {
		send -- "OK\r"
	}
}

expect -exact "Press <enter>"
send -- "\r"

set timeout 1

expect -exact "Enter Option (or Q to Quit):"
send -- "Q\r"

expect "prduc@cms-contactless-acquiring-dev-se$ "
send -- "exit\r"

expect eof
