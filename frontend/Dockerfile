FROM python:3.10.21-alpine3.24

WORKDIR /app

COPY . .

RUN pip install -r requirements.txt

EXPOSE 5002 

# 5002 this is for local dev

CMD ["python", "app.py"]
