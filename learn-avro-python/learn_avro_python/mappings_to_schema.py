import json


def read_json_file(file_path):
    with open(file_path, "r") as data:
        return json.load(data)


def write_json_file(file_path, data):
    with open(file_path, "w") as out:
        out.write(json.dumps(data, indent=4, sort_keys=False))


ES_TO_AVRO_TYPES = {
    "text": "string",
    "keyword": "string",
    "date": "string",
    "boolean": "boolean",
    "double": "double",
    "float": "float",
    "integer": "int",
    "long": "long"
}


def create_schema_fields(mappings):
    fields = []
    for name in mappings:
        mapping = mappings[name]
        type = mapping.get("type")
        avro_type = ES_TO_AVRO_TYPES.get(type, "FIXME")
        field = {"name": name, "type": [avro_type, "null"]}
        fields.append(field)
    return fields


def create_schema(namespace, name, doc, type, fields):
    return {
        "name": name,
        "namespace": namespace,
        "doc": doc,
        "type": type,
        "fields": fields
    }


def create_channel_schema():
    file_path = "./mappings/channel-v2.json"
    mappings = read_json_file(file_path)
    fields = create_schema_fields(mappings)
    schema = create_schema("icx", "ChannelV2", "Channel entity v2", "record", fields)
    write_json_file("./schemas/channel-v2.schema.json", schema)
    print(schema)


def create_source_schema():
    file_path = "./mappings/source-v2.json"
    mappings = read_json_file(file_path)
    fields = create_schema_fields(mappings)
    schema = create_schema("icx", "SourceV2", "Sources entity v2", "record", fields)
    write_json_file("./schemas/source-v2.schema.json", schema)
    print(schema)


def create_consumer_schema():
    file_path = "./mappings/consumer-v2.json"
    mappings = read_json_file(file_path)
    fields = create_schema_fields(mappings)
    schema = create_schema("icx", "ConsumerV2", "Consumer entity v2", "record", fields)
    write_json_file("./schemas/consumer-v2.schema.json", schema)
    print(schema)


def create_interaction_schema():
    file_path = "./mappings/interaction-v2.json"
    mappings = read_json_file(file_path)
    fields = create_schema_fields(mappings)
    schema = create_schema("icx", "InteractionV2", "Interaction entity v2", "record", fields)
    write_json_file("./schemas/interaction-v2.schema.json", schema)
    print(schema)


def create_twitter_schema():
    file_path = "./mappings/twitter-v3.json"
    mappings = read_json_file(file_path)
    fields = create_schema_fields(mappings)
    schema = create_schema("icx", "TwitterV3", "Twitter entity v3", "record", fields)
    write_json_file("./schemas/twitter-v3.schema.json", schema)
    print(schema)


def create_tweet_schema():
    file_path = "./mappings/tweets-v3.json"
    mappings = read_json_file(file_path)
    fields = create_schema_fields(mappings)
    schema = create_schema("icx", "TweetV3", "Tweet entity v3", "record", fields)
    write_json_file("./schemas/tweet-v3.schema.json", schema)
    print(schema)


if __name__ == "__main__":
    create_channel_schema()
    create_consumer_schema()
    create_source_schema()
    create_interaction_schema()
    create_twitter_schema()
    create_tweet_schema()
