#Persistent
#SingleInstance force
;SetKeyDelay, 25
#t::
FileSelectFile, SelectedFile
if SelectedFile =
    MsgBox, The user didn't select anything.
else
    Loop
    {
        FileReadLine, line, %SelectedFile%, %A_Index%
        if ErrorLevel
            break

	    ifequal line,BREAK
	        Sleep 2000
         else
         {
            SendInput %line%
	        Sleep 100
         }
    }

return