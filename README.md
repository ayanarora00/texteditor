# CSC 207: Text Editor

**Author**: Ayan Arora

## Acknowledgements

+ Prof. Osera, mentor David Rhoades and Grinnell CS Department
+ Prof. Osera especially helped fix the windows - lanterna working issue

## Resources Used

+ Java version 17
+ Visual Studio Code IDE
+ Text editor prompt on TTAP

## Complexity of insertion in Simple String Buffer vs. Gap Buffers

+ SimpleStringBuffer insert is linear or O(n):

    There are 3 main operations that take place - formation of 2 substrings and their concatenation with the character to be inserted. Since, Java strings are immutable, the 2 substring functions create 2 new strings reading up to n together. Concatenation forms a final string of n+1 characters. Therefore, there are 2n + 1 character copies overall. 

+ GapBuffer insert is constant time or O(1):

    The critical operation that happens is simply inserting a character at the start position (one write). However, when the capacity of the buffer is maximised out, we have to expand it by doubling it in size. This happens by copying elements from the original buffer onto the new buffer which takes n character copies and n character writes (2n) giving it linear time
    or O(n). Overall, though, since this happens much more rarely, I'll call the GapBuffer insert a constant time operation.

## Changelog

+ Implemented the stylistic changes requested
+ Implemented all the functions in SimpleStringBuffer and GapBuffer as instructed.
+ Implemented the unit test suites for SimpleStringBuffer and GapBuffer classes and verified they work
+ Implemented drawBuffer and main methods in TextEditor.java
+ Included a sample text file called "sample.txt" in the directory.

## Revision log (changes for final submission)

+ Implemented property testing as requested initially by prompt
+ Also added additional unit tests to GapBuffer testing suite related to expansion of buffer/edge cases as requested
+ Time Complexity analysis added to the README for both gap buffers and string buffers
