from fastavro import writer, reader, parse_schema
import io
import os
import json


# def read_json_file(file_path):
#     with open(file_path, 'r') as data:
#         return json.load(data)
#
#
# def read_raw_file(file_path):
#     with open(file_path, 'r') as data:
#         return data.read()


def read_avro_file(file_path):
    with open(file_path, 'rb') as fo:
        avro_reader = reader(fo)
        for record in avro_reader:
            print(record)


def main():
    # file_path = "/Users/jeffbritton/Code/personal/learn-avro/learn-avro-clj/base-entity.avro"
    file_path = "/Users/jeffbritton/Code/personal/learn-avro/learn-avro-clj/youtube-channel.avro"
    read_avro_file(file_path)


if __name__ == "__main__":
    main()
