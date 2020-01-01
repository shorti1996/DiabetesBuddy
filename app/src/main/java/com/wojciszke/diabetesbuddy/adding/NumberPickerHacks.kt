package com.wojciszke.diabetesbuddy.adding

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.NumberPicker


/**
 * This should be a new widget implementation.
 *
 * XXXXXXXXXXXXXXXXXK00OOOOOO00OOOOOOO0000000O0000000000000000KKKKKXXXXXXXXXXXXXXXXXXNNWMMMMWNWMMMMMMMMWWWWWWWKxc,,,,'',:;;:;;:cccccccclllloooooooddddc..
 * XXXXXXXXXXXXXXXXXXKK00OOO00000000OOO0OOO0OOO000OOOOOOOOOOOOOOO0KXXXXXXKKKXXXXXXXXNWWWWWWWWNNNWWMMMMMMMMWWWMMWKkl:;:::c::llllodxxkkkkkkOOOkxxkO000OOkl.
 * KXKKKKKKKXXKKKXXKKKKKKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXKKKKKKKKKKKKKKKKXNWNWWNXXXXXX00XNWNWMMWWNNWWWWWWN0xxxddxO0000KKXXXXXXXXXXXK00KXXXKK00Od
 * KKKKKKKKKKKKKKKK00K0KKKKKXXXXKKKKKKXXXXXXXXXXXXKKXXXXXXXXXXXXXXXKKKKKKKKKKKKKKKXNWNX0OxxkO00000KOkkO0K0Oxx0XNNNNWKxdxkkk0KKKXXXXXXXKKXXXKK0KKKKKK0OOkx
 * KKKK00KKKKKKKKKKK000KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK00KKK00KXXXNX0Oxoccccccllccc:,....',lxdxO0XNNWWKkkkkxxk0KKKKKKKXKKKKKKKKKKKKKK00Okk
 * 00000000KXKKXKKKKK0KKKKXXKK000000KK0000K0K00KKKKKKKKKK000KKKKKKK00000000O0KXX0xc:;,'',,''....'',,,'.  ..;:ldxOXNXX0kxdodddk000K0K0KKKKKKK00KKKK00OOOOO
 * 00OOO000KXKKK000K000000KKK00000000000000000000000000000000000000000000OOOOkol;',,,,,,,,,,,.......';;,'''',,',:okOOkdl:,,;lxO0KKKKKKKK0KKK0000000OOkkkO
 * OOOOOO00KKKKK000K00OOO0K00OOOOOOO0000000O00000OO000OO00OOOO00O0OOOOOOOOOxc'..',:c;,,'..',;:,......,,,,,,;::::;;coxko:,'';dkO0KKKKKKXK0KXK0O0000OOkxdkK
 * OOkOOOOO0K00000000OOOO0OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOd;..''';cc;''....',::,.....''''',,;;:c:;:llodc'';dkkO0K0KK00KK0KK00OOOOOkkxdoxK
 * kkkkkkOOOOOkOOOOOkkkkkOkkkkkkOOOOOOOOOOOOOkkkkOOOOOOOOOOOOkkOOOOOkOOkkx;.''..';:cc;,.....':cc,..........'',,;c;';clodllxkkkkO00KK0KKKKKKK0OOOOkkxdold0
 * kkkkkkkkkkkkkkkkkkkkkkOkkkkOOOOOOOOOOOOkkOOOkkOOOOOOOkkOOOkOOOOkOOkkkkc......;:ccccc:,''',coolc;'...  .....'',:;,;odlcdkkkkkOOOOOOOOOOO0OOkkkkkkxdoc::
 * kxxkkkkkkkkkkkxxxxxkkxkkkkkkkkkkkkkkkkkkxkkkkkkkkkkkkkkkkkkkkkkkkkkkkx;.. .',cllc::::c:::cccc::c::;;'..  .....',,',locoxxxxxxxxxxxxxxxxkkxkkxxxxddol:,
 * xxxxxxxkkxxxxxxxxxxxxxxxxxxkxxxxxxxxxxxkxkkxxxxxxxxxxxkxxxxxxxxxkxxxxd;...';::;,....';:;,.....';:loddol;'.    ..'..,ldooddddxxxxxxxxxxxxxxxxxxxdddddol
 * xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxddd;...:ccc;'......,;;,'....',:clodxxdl;.    ...'cdc:ooooddddddddddxxxxxxxxxddddddd
 * dddddxxxddxxxxxxdxxxxdxddddddxxxdddxxxxxdddxxxddxxxxxxdddxxxdddxxxxxddc,',;;,,'......';lol;'......',,;:loddl'    ...';,;lllooooooooooooddddddddddooood
 * dddddddddddddddddxxxxxxxxxxxxxxdddxdxxxxxxxxxxxxddddddddddddddddddddddo:,,.........'';ldxoc;,'..'',;:lodxkkkd:.  .. ...',;;::::::c::cclllllooooooooooo
 * ooddddddddddddddddxdddddddxxxdddddddxxxddddddddddddddddddxdddooddooooolc::;,,'',,;;;:lxkkxxoolc::::codxkOOOOkxc'.'.....''''',,;;,,,,;;;:::ccccccllllll
 * lllllolooooollooooooooooooodddddddddddddooddddddooooooc:cc::::lolllllccclollcccclcccoxkOOkxdolodxxkkOO000OOkxkxlc;'.....''....''.'''''''',,;;;;::::ccc
 * ccllcccccccccclllllllllllllooooooooooooolllooooolllllc;,,,,,,,:cccccc:::ldddddoccclodxxkOkxxdc;:lodxkOO0OOkxxxxxxo:;c,...............'''..''''',,;;;;:
 * :::::::::::::ccccccccccccclllllllllllllllllllllllllccc:::::::;:c:::::;;:llllc:;,,,;;;;;;::;,,;;'',:cloddddoodxkxxxococ................''..'''''',,,,,;
 * ;;;;;;;::;;:::::::cc::cc:c::cccccccc:cccccccccccccccclcccccccc:::;;::;;;::;;,'............';:cc;'..',;:ccllodxkxxxddxc.......................''''''',,
 * ,,;;;;;;;;;;;;::::::::::::::::::::::::::ccccccccccccccccccccc::::;;;;;,,,,,''....',,,,;;:loddddoc,...,,;cllodxxddl:lo;........................'''''',,
 * ,;;;;;;;;;;;;;:::::::;;;;;:::;:::::::::::::::::::;;:::::::::::;;;;;;;;,,;,'.....';:ccccllodxxxxdo:,..',;clloooooo:.,,.........................'',,''''
 * ,,,;;;,;;;;;;;;;;::::;;;;;;;;;;;:;;;;;;;;;;;;;;:;;;::::::;:ccccc::;;;;;;;;'.....',,,,,,,,,;:clloll:,..,;:llllllol:;;,''......'...........',;;,,;:;;;;,
 * ;,,;;;;;,,''''...',;;;;;;;;;;:;;,;::::;,;;;;;:::::ccccllc:clcc::::;;;;;;;;,''.........''','.....':c;'',;:clllllll:;;;;;;,'.''...........',;;;;;;;,;;;,
 * ,,,,;,'.....     .............................',,,,,,,,,,''''''''''''''',,,,,'..'...     .    .,:ll:,;;::cclccllc,'...................................
 * ...............................................................''.'''.....'''''';:c:;::;,,;;:codddocc:::::ccccloc'....................................
 * ..''....''..............................................''..''''''''''''''''',,,;::clloddooolllooolccc::::::ccloc'.'''''''''''''''''''''''''''''''''''
 * ''''',''''''''''''''''''''.......................''''''''',,,''',,,,'',,,,,,,,,,,,,,,,;;;,,,,;:::::ccc:::::::cloc,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
 * ;;;;;;;,,,,,,,,,,'',,,,,,,,,,'''''''''''''''''''''''''''''',,,,,,,,,,,,,,,,,;;;;;,''........',;;;::::;;;;;;;:cloc;,,,,,,,,,,,,,',,,;;;;;;;;;;;;;;;;;;;
 * :::::::;;;;;;;;;;,;;;;;;;;;;;;,,,,,,,,,,''''''''''''''''''',,,,,,,,,,,,,,'',,,,,;;,,'''...'',,;;;;;,,,,,,,,;;:col;;;:;;;;;;;;;;;;;;::::::::::ccccccccc
 * ccccccccc::::::::;;;;;;;;;:::::;;;;;;,,,,,,,,,,,,,,,,,,,,,,,;;;;;;;;;,,,,,,,,;cddc,'''''''''''',,'''''''',;;;:coxxxdoollllllllllllllloooooooooooooolll
 * llllllllllllllccccccccllllooooolllllcccccccccccccccc:::ccccccccccclllllllooddxkdl:,''...............'''',;:cc:cox0KOxdolcldxxdxxxxddddddxxxxxxxxxddooo
 * ddddddoooodddddooooooodddddxxxxxddddddddddddddddddddddddddddxxxxxxxkkkOOO0KKKklll:,''...............'',,;:::cccloxxdx0kolcoO0OOkxxxdddxxxxkkkkkkxxxxkx
 * kxxxxxxxxxxxxxxxxddddddddooddddooooooooooooooooddoddddddxkO000000000000OOOOOxlcoc;,''......... .....,;;;;::::ccccclox0xlxkx0XXK00kxdoolloooooooooooooo
 * dooooollllllllllllllllllccccccccccc:::::::::::ccc:cccloxO0K0000OOkkkkkkkO0OOd:llc:;,'..............',;;;;;:::::::;;:lloxdxO0XXXXXXXKK0Okkxdolcccccllll
 * cccccccccccccccccccccccccccccc::::::::::cclcccllcccccokO0OOOOkkkxxdxddxOKXKKd:llc::;,'..............',,;;;:ccccc:;;;;:cododO0XKKKKKKKKXXXXXK0kxdoolcll
 * :::::::::ccc::cccc::::::::::::;;::::cccllooddooolooooxkkkkxxxxddddoddoxKXX0kl;clc::;,,''............',;;;:cllllllccc:cldxxxxOKKKKK0000000KKKXXXXXK0kxd
 * llllllooooooooooooooooooooooooooooooooddoooddddddooooddddddddoooooooodOXXXOdc:llc:::;;,,''........'',;:;;:cclooooollldkkdxkxxOKK00OOkkkkkOO0KKKXXXNNX0
 * xxxxxxxxxxxxxkkxxxddxxxdddddddddddddxxxxxxxxxxxxxxdooooooooooolllloookXXXX0xocllcc::;;;,,,''..''',,,;::;;:ccloooddddxkkkxkOkkk0K00OkkxxxxkkO00KKKXXXXX
 * xxxxxxxxxkkxxkkkkkkkkkkkkkkkxxkkkxxxkxkkkkkkkkkkkxdollllllllllllllooxKXXXKkdlcclcc:::;;;,,,,'''',,,;;:::::ccllooddxxxxkxxxOOxkO000OOkxxddxkOO00KKKKKXX
 * kkkkkkOOOOOOOOOOOkkOOOOOOOOOOkkkkkkkOkkOOOOOOkkkkxoollllllllllllloloOXXKX0o;,,clccc::;;;;;,,,,,,,;;;::ccccclllooddxkkkxxxxOkxkO000OOkxddddxkkOO0000KKK
 *
 * Ain't nobody got time for that
 */
object NumberPickerHacks {

    fun findInput(np: ViewGroup): EditText? {
        val count = np.childCount
        for (i in 0 until count) {
            val child: View = np.getChildAt(i)
            if (child is ViewGroup) {
                findInput(child)
            } else if (child is EditText) {
                return child
            }
        }
        return null
    }

    fun focusNextOnInputTextWatcher(thisNumberPicker: NumberPicker, nextNumberPicker: NumberPicker?) =
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val inputString = s.toString()
                    if (inputString.isNotEmpty()) {
                        thisNumberPicker.value = inputString.toInt()
                        if (nextNumberPicker != null) {
                            nextNumberPicker.performClick()
                        } else {
                            thisNumberPicker.hideKeyboard()
                            thisNumberPicker.clearFocus()
                        }
                    }
                }
            }

}

fun View.hideKeyboard() {
    val imm: InputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}
