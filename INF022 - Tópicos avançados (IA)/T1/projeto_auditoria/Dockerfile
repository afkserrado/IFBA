FROM python:3.12-slim

ENV PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1

# Instala Tkinter e bibliotecas gráficas mínimas exigidas pelo OpenCV/RapidOCR
RUN apt-get update && apt-get install -y --no-install-recommends \
    python3-tk \
    libgl1 \
    libglib2.0-0 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY config.py cliente_ollama.py leitor_documentos.py main.py ./
COPY prompts/ ./prompts/

RUN mkdir -p /root/Downloads

CMD ["python", "main.py"]