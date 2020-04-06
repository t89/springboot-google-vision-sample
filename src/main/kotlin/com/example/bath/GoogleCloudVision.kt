package com.example.bath

import com.google.cloud.vision.v1.AnnotateImageRequest
import com.google.cloud.vision.v1.Feature
import com.google.cloud.vision.v1.Feature.Type
import com.google.cloud.vision.v1.Image
import com.google.cloud.vision.v1.ImageAnnotatorClient
import com.google.protobuf.ByteString

import java.io.IOException
import java.io.File

class GoogleCloudVision() {

    fun run(): String {
        val imageFileName = "./src/main/resources/kitti.jpg" // Image file path
        val imageFile = File(imageFileName)
        if (!imageFile.exists()) {
            throw NoSuchFileException(file = imageFile, reason = "The file you specified does not exist")
        }
        var returnString = ""
        try {
            returnString = quickstart(imageFileName)
        } catch (e: IOException) {
            println("Image annotation failed:")
            println(e.message)
        }
        return returnString
    }

    fun quickstart(imageFileName: String): String {
        // [START vision_quickstart]
        // import com.google.cloud.vision.v1.ImageAnnotatorClient
        // import java.io.File
        val imgProto = ByteString.copyFrom(File(imageFileName).readBytes())
        val vision = ImageAnnotatorClient.create()

        // Set up the Cloud Vision API request.
        val img = Image.newBuilder().setContent(imgProto).build()
        val feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build()
        val request = AnnotateImageRequest.newBuilder()
                .addFeatures(feat)
                .setImage(img)
                .build()

        // Call the Cloud Vision API and perform label detection on the image.
        val result = vision.batchAnnotateImages(arrayListOf(request))

        val sb = StringBuilder()

        result.responsesList[0].labelAnnotationsList.forEach { label ->
            val label_text = "${label.description} (${(label.score * 100).toInt()}%)"
            println(label_text)
            sb.append(label_text).append(" ")
        }
        return sb.toString()
        // [END vision_quickstart]

    }
}
