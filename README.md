# learn-avro

## Overview

Current Code
- Demonstrates how to define an avro schema in a language format (json): see `schema/` directory.
- Demonstrates how to serialize/deserialize a message in different languages
- Serializes a message from Clojure and deserializes the message in Python

Future Experiments
- Serialize in Python and deserialize in Clojure
- Serialize in Go and deserialize in Python
- Serialize in Go and deserialize in Clojure
- Try compression using Snappy (seems to be recommended due to optimal speed/compression ratio)
- Determine how to handle null, empty fields, or other problematic data
- Determine if it's possible to omit fields not specified in schema in serialization process.  Are there other approaches?

Findings
- Be careful with Clojure keywords and naming when defining/reading schemas and serializing messages.
- Python's `fastavro` seems to be recommended as the fastest.  The official `avro` and `avro-python3` are rumored to be slow.


## Installation

Install python project dependencies:
```bash

# navigate to the python project root
cd learn-avro-python

# install dependencies
pip install -r requirements/requirements.txt

```

Install clojure project dependencies:
```bash

# navigate to the python project root
cd learn-avro-clj

# install dependencies
lein deps

```

## Quick start

Run clojure code to serialize YouTube channel avro file:
```clojure 
(serialize-youtube-channel)
```

Run python code to deserialize YouTube channel avro file:
```bash 
python learn_avro_python/main.py
```

