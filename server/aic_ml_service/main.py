from fastapi import FastAPI, UploadFile, File
from fastapi.middleware.cors import CORSMiddleware
import numpy as np
import tensorflow as tf
from PIL import Image, ImageOps
import uuid
import shutil
import os

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.post("/relief_image/predict")
def read_root(relief_image: UploadFile = File(...)):
    np.set_printoptions(suppress=True)

    model = tf.keras.models.load_model('model/final_relief.h5')

    data = np.ndarray(shape=(1, 224, 224, 3), dtype=np.float32)

    generate_uuid = uuid.uuid4().hex
    file_name_uuid = '{}_{}'.format(generate_uuid, relief_image.filename)
    with open('temp/{}'.format(file_name_uuid), 'wb') as image:
        shutil.copyfileobj(relief_image.file, image)
    image = Image.open('temp/{}'.format(file_name_uuid))

    size = (224, 224)
    image = ImageOps.fit(image, size, Image.ANTIALIAS)

    image_array = np.asarray(image)

    normalized_image_array = (image_array.astype(np.float32) / 127.0) - 1

    data[0] = normalized_image_array

    prediction = model.predict(data)
    print(prediction)

    pr = np.argmax(prediction, axis=1)

    if pr[0] == 0:
        result = {"name": "Relief Ari Darma",
                  "description": "Menggambarkan terbebas-nya  naga  betina  dari  rudapaksa  naga  jantan dan pergi atau pulang menemui ayahnya yang  merupakan  raja  para  naga.  Naga  betina menceritakan  pada  ayahnya  budi  baik Ari Darma  yang  telah membebaskan dirinya dari perbuatan dosa dan aib yang memalukan. Gambaran relief untuk cerita ini adalah masih nampak suasana hutan. Selanjutnya nampak naga betina sedang bergerak dan menemui  seekor  naga  dalam  posisi  duduk dengan  ekor  menjulur  ke  atas.  Sosok  kedua naga ini menggambarkan sebagai bangsawan nampak pada mahkotanya, dan mahkota ayahnya  nampak  lebih  menandakan  sebagai raja"}
    elif pr[0] == 1:
        result = {"name": "Relief Mahabrata",
                  "description": "Menggambarkan Pandawa dan Kurawa berjudi dadu untuk bertaruh kekayaan. Saat pertaruhan itu terjadi, Dursasana, salah satu anggota Kurawa mempermalukan Drupadi dengan cara menarik pakaian Drupadi. Sehingga, Drupadi telanjang dan akhirnya ia berusaha menutupi tubuhnya kembali dengan membuka gelungan rambutnya. Gelungan rambut Drupadi ini menginformasikan bahwa tradisi menggelung rambut telah ada sejak dulu. Akibat kejadian tersebut, akhirnya Pandawa memutuskan untuk pergi dari kerajaan dan menuju hutan. Tidak hanya anggota Pandawa saja yang pergi, tetapi mereka juga membawa serta Kunti (ibu Pandawa), Drupadi, dan punakawan (pembantu dan pengasuh Pandawa)."}
    else:
        result = {"name": "Releief Arjunawihaha",
                  "description": "Menggambarkan sang Arjuna sedang laku tapabrata di gunung Indrakila. Dalam laku taparatanya, Arjuna mendapat ujian dari para dewa untuk melihat keteguhan sang Arjuna dalam lakunya. Para Dewa mengutus tujuh Bidadari untuk menggoda Arjuna dalam laku tapa. Diantara tujuh bidadari itu ada yang sangat terkenal cantiknya, yaitu yang bernama Dewi Supraba dan Dewi Tilottama. Ternyata Arjuna teguh dalam laku tapanya tak tergoda oleh tujuh bidadari itu. Dengan rasa kecewa para Bidadari kembali ke kahyangan dan melaporkan atas kegagalannya kepada Bathara Indra."}

    os.remove('temp/{}'.format(file_name_uuid))
    return result
