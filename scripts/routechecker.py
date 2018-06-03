#!/usr/bin/env python3

import sys
import os.path
import csv


def print_usage():
    print("Usage: python routechecker <route.csv>")


valid_rangetypes = ["All", "Even", "Uneven"]
valid_directions = ["LowToHigh", "HighToLow"]


def main():
    if len(sys.argv) == 2:
        filename = sys.argv[1]
        if not os.path.isfile(filename):
            print("Invalid filename")
            print_usage()
            exit(2)
        with open(filename, "r") as csvfile:
            reader = csv.reader(csvfile)
            valid = True
            for row in reader:
                # print(row)
                if len(row) == 5:
                    name = row[0]
                    lowerbound = int(row[1])
                    upperbound = int(row[2])
                    rangetype = row[3]
                    direction = row[4]
                    valid_name = name == name.strip() != ""
                    valid_range = (upperbound >= lowerbound)
                    # print("Lowerbound: {}, upperbound: {}".format(
                    # lowerbound, upperbound))
                    valid_rangetype = rangetype in valid_rangetypes
                    valid_direction = direction in valid_directions
                    valid_row = valid_name and valid_range and valid_rangetype and valid_direction
                    # print("Range: {}, rangetype: {}, direction: {}".format(
                    # valid_range, valid_rangetype, valid_direction))
                    if not valid_row:
                        print("Invalid row: {}".format(row))
                        valid = False
                else:
                    print("Invalid row: {}".format(row))
                    valid = False
            if valid:
                print("File is valid")
                exit(0)
            else:
                exit(1)
    else:
        print_usage()
        exit(2)


if __name__ == "__main__":
    main()
