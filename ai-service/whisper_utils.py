import whisper

model = whisper.load_model("base")

def transcribe_audio(file_path: str):
   
    result = model.transcribe(file_path, verbose=False)
    
  
    segments = []
    for segment in result['segments']:
        segments.append({
            "start": segment['start'],
            "end": segment['end'],
            "text": segment['text']
        })
    return {"full_text": result["text"], "segments": segments}
