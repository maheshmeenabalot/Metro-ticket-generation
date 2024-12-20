from googleapiclient.discovery import build
from google.oauth2.credentials import Credentials
from google_auth_oauthlib.flow import InstalledAppFlow
from google.auth.transport.requests import Request
import os
import base64
from email.mime.text import MIMEText

# If modifying these SCOPES, delete the token.json file.
SCOPES = ['https://www.googleapis.com/auth/gmail.readonly']

def get_gmail_service():
    """Authenticate and get the Gmail service"""
    creds = None
    # The token.json stores the user's access and refresh tokens
    if os.path.exists('token.json'):
        creds = Credentials.from_authorized_user_file('token.json', SCOPES)
    # If there are no valid credentials, log in again
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = InstalledAppFlow.from_client_secrets_file(
                'credentials.json', SCOPES)
            creds = flow.run_local_server(port=0)
        # Save the credentials for the next run
        with open('token.json', 'w') as token:
            token.write(creds.to_json())
    return build('gmail', 'v1', credentials=creds)

def get_last_200_emails():
    """Fetch the last 200 emails from Gmail"""
    service = get_gmail_service()
    results = service.users().messages().list(userId='me', maxResults=200).execute()
    messages = results.get('messages', [])

    print("Last 200 Emails (Sender and Subject):\n")
    for msg in messages:
        msg_data = service.users().messages().get(userId='me', id=msg['id']).execute()
        headers = msg_data['payload']['headers']

        # Extract sender and subject from the email headers
        sender = next((header['value'] for header in headers if header['name'] == 'From'), "Unknown Sender")
        subject = next((header['value'] for header in headers if header['name'] == 'Subject'), "No Subject")
        print(f"Sender: {sender}")
        print(f"Subject: {subject}")
        print("-" * 50)

if __name__ == '__main__':
    get_last_200_emails()
