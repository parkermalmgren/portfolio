#####################################
# Parker Malmgren
# CSC 201 - Gourd
# Palindrom Checker- checks to see
# If a word or phrase is a palindrome
########################################



from CSC201UT import Deque


def check_phrases(phrases):
    # Check in deque for if it is palindrome
    for phrase in phrases:
        # Removing the non-letter characters (stack overflow for lower and isalpha)
        stripped_phrase = ''.join(filter(str.isalpha, phrase.lower()))

        # Instantiate deque and adding char (also from stack)
        deque = Deque()
        for char in stripped_phrase:
            deque.add_rear(char)

        # Compare characters from head and tail (ideas from stackoverflow)
        is_palindrome = True
        while deque.size() > 1:
            head = deque.remove_front()
            tail = deque.remove_rear()
            # Return if it palindrome or not
            if head != tail:
                is_palindrome = False
                break

        if is_palindrome:
            print(f'"{phrase.strip()}" is a palindrome.')


# Prompt user for input type making it all lowercase and stripped
user_input = input("Check a 'File' or a 'Phrase'? ").strip().lower()

# If file, go through as file process
if user_input == "file":
    file_path = input("Enter the file path: ")
    with open(file_path, 'r') as file:
        phrases = file.readlines()
    check_phrases(phrases)

# If phrase, go through phrase process
elif user_input == "phrase":
    phrase_input = input("Enter your phrase: ").strip()
    check_phrases([phrase_input])  # Phrase needs to be passed as a list

else:
    print("Not a valid answer. Please enter 'File' or 'Phrase'.")