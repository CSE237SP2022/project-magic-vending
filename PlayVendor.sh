#!/bin/bash

# This script compiles and executes our program.

cd src/test
javac Vendor.java
echo "Switched to directory /src/test. Compiled and running program Vendor.java"
java Vendor
