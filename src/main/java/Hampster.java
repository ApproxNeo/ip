import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import task.*;

public class Hampster {
    public static void main(String[] args) {

        final String LINE = "\t____________________________________________________________\n";

        /*
         * Created with
         * https://patorjk.com/software/taag/#p=display&f=ACID+3D+Blue&t=Hampster&x=none&v=4&h=4&w=80&we=false&ft=thedraw
         */
        String banner = """
                [0;97;40m▄▄▄▄▄▄▄▄[0;37;40m▄[0;90;40m▄[0;97;40m▄▄▄▄▄▄▄▄[0;37;40m▄▄[0;90;40m▄[0;37;40m    [0;97;40m▄[0;97;47m▀▀▀▀▀▀▀[0;97;40m▄[0;37;40m          [0;97;40m█[0;97;47m▀▀▀▀▀▀▀▀▀▄[0;90;47m▀[0;37;40m▄[0;90;40m▄[0;37;40m [0;97;40m▄[0;97;47m▀▀▄[0;90;47m▀[0;37;40m▄[0;90;40m▄[0;37;40m        [0;97;40m▄▄[0;97;47m▀▀▀▀▀▄▄[0;90;47m▀[0;37;40m▄[0;90;40m▄[0;37;40m    [0;97;40m█[0;97;47m▀▀▀▀▀▀▀▀▀▀▄▄[0;90;47m▀[0;37;40m▄[0;90;40m▄[0;37;40m    [0;97;40m▄[0;97;47m▀[0;97;40m▀▀▀▀▀▀▀▀▀▀▀▀▀[0;97;47m▄▄[0;90;47m▀[0;37;40m▄[0;90;40m▄[0;37;40m     [0;97;40m▄[0;97;47m▀[0;97;40m▀▀▀▀▀[0;97;47m▄▄[0;90;47m▀[0;37;40m▄[0;90;40m▄[0;37;40m [0;97;40m█[0;97;47m▀▀▀▀▀▀▀▀▀▀▄▄[0;90;47m▀[0;37;40m▄[0;90;40m▄[0;37;40m    [0m
                [0;97;40m█[0;37;40m█▀▀▀▀▀[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;37;40m█▀▀▀▀▀[0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m   [0;97;40m█[0;97;47m [0;37;40m▀  [0;96;40m▄▄■[0;37;40m [0;97;40m█[0;97;47m [0;90;40m▄[0;37;40m        [0;97;40m█[0;37;40m█       [0;96;40m▄[0;37;40m [0;97;40m▀[0;97;47m▄[0;37;40m█[0;97;40m█[0;37;40m  [0;96;40m▄[0;37;40m [0;97;40m▀[0;97;47m▄[0;37;40m█[0;90;47m▀[0;90;40m▄[0;37;40m    [0;97;40m▄[0;97;47m▀[0;37;40m▀   [0;96;40m■[0;37;40m [0;96;40m▄▄[0;37;40m [0;97;40m▀[0;97;47m▄[0;37;40m█[0;90;47m▀[0;90;40m▄[0;37;40m  [0;97;40m█[0;37;40m█      [0;96;40m■[0;37;40m [0;96;40m▄▄[0;37;40m [0;97;40m▀[0;97;47m▄[0;37;40m█[0;90;47m▀[0;90;40m▄[0;37;40m [0;97;40m█[0;37;40m█ [0;96;40m▄▀▀■·[0;37;40m  [0;96;40m·■▄[0;37;40m     [0;97;40m█[0;37;40m█[0;90;47m▀[0;90;40m▄[0;37;40m  [0;97;40m▄[0;97;47m▀[0;37;40m▀        [0;97;40m█[0;37;40m█[0;90;47m▀[0;90;40m▄[0;97;40m█[0;37;40m█      [0;96;40m■[0;37;40m [0;96;40m▄▄[0;37;40m [0;97;40m▀[0;97;47m▄[0;37;40m█[0;90;47m▀[0;90;40m▄[0;37;40m  [0m
                [0;97;40m█[0;37;40m█ [0;96;40m▄■[0;37;40m  [0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;37;40m█ [0;96;40m▄■[0;37;40m  [0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m   [0;97;40m█[0;97;47m [0;37;40m [0;96;40m▄▀[0;37;40m     [0;97;40m█[0;37;40m█[0;90;47m█[0;37;40m       [0;97;40m█[0;37;40m█ [0;34;40m░[0;37;40m      [0;96;40m▀▄[0;37;40m [0;97;40m▀[0;37;40m    [0;96;40m▀▄[0;37;40m [0;97;40m█[0;37;40m█[0;90;47m▀[0;90;40m▄[0;37;40m  [0;97;40m█[0;37;40m█         [0;96;40m▀▄[0;37;40m [0;97;40m█[0;37;40m█[0;90;47m▀[0;90;40m▄[0;37;40m [0;97;40m▀[0;97;47m▄[0;37;40m▄         [0;96;40m▀▄[0;37;40m [0;97;40m█[0;37;40m█[0;90;47m▀[0;90;40m▄[0;97;40m█[0;37;40m█[0;96;40m·[0;37;40m          [0;96;40m▀■·[0;37;40m  [0;97;40m█[0;97;47m [0;37;40m█[0;90;40m█[0;37;40m [0;97;40m█[0;37;40m█  [0;96;40m▄■·[0;37;40m     [0;97;40m█[0;97;47m [0;37;40m█[0;90;40m█[0;97;40m▀[0;97;47m▄[0;37;40m▄         [0;96;40m▀▄[0;37;40m [0;97;40m█[0;37;40m█[0;90;47m▀[0;90;40m▄[0;37;40m [0m
                [0;97;40m█[0;97;47m [0;96;40m▐[0;37;40m    [0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;97;47m [0;96;40m▐[0;37;40m    [0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m  [0;97;40m▐[0;97;47m▌[0;37;40m▌[0;96;40m▐[0;37;40m  [0;97;40m▄[0;37;40m     [0;97;40m█[0;37;40m█[0;90;47m█[0;37;40m      [0;97;40m█[0;37;40m█[0;34;40m▒▒░░[0;37;40m [0;97;40m▄▄[0;37;40m▄[0;34;40m░[0;37;40m [0;96;40m▌[0;37;40m  [0;97;40m▄▄[0;37;40m▄[0;34;40m░[0;37;40m [0;96;40m▌[0;97;40m▐[0;97;47m▌[0;37;40m█[0;90;47m▐[0;90;40m▌[0;97;40m▐[0;97;47m▌[0;37;40m▌    [0;97;40m▄[0;97;47m▀[0;97;40m▄[0;37;40m▄   [0;96;40m▌[0;97;40m▐[0;97;47m▌[0;37;40m█[0;90;47m▐[0;90;40m▌[0;37;40m [0;97;40m▐[0;97;47m▌[0;37;40m▌   [0;97;40m█[0;97;47m▀[0;97;40m▄▄▄▄▄▄█[0;97;47m▌[0;37;40m█[0;90;40m█[0;97;40m▀[0;97;47m▄▄[0;97;40m▀▀█[0;97;47m▌[0;37;40m▌ [0;34;40m░[0;37;40m  [0;97;40m▄[0;97;47m▀▀▄[0;97;40m▄[0;97;47m▀[0;37;40m█[0;90;47m▄[0;90;40m▀[0;37;40m [0;97;40m▐[0;97;47m▌[0;37;40m▌[0;96;40m▄▀[0;37;40m  [0;97;40m▄[0;97;47m▀▀[0;97;40m▄▄[0;97;47m▀[0;37;40m█[0;90;47m▄[0;90;40m▀[0;37;40m  [0;97;40m▐[0;97;47m▌[0;37;40m▌   [0;97;40m█[0;97;47m▀[0;97;40m▄[0;37;40m▄   [0;96;40m▌[0;97;40m▐[0;97;47m▌[0;37;40m█[0;90;47m▐[0;90;40m▌[0m
                [0;97;40m█[0;37;40m█  [0;94;40m▄▄[0;37;40m [0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;37;40m█  [0;94;40m▄▄[0;37;40m [0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m  [0;97;40m▐[0;97;47m▌[0;37;40m▌[0;96;40m│[0;37;40m [0;97;40m▐[0;97;47m▌[0;97;40m█[0;37;40m     [0;97;40m█[0;37;40m█[0;90;47m█[0;37;40m     [0;97;40m█[0;37;40m█[0;34;40m▓▓▓▓[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;44m░[0;34;40m▓▒░[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;44m░[0;34;40m▓▒░[0;97;40m█[0;37;40m██[0;90;40m█[0;97;40m█[0;37;40m█    [0;97;40m█[0;37;40m██[0;90;40m█[0;97;40m█[0;37;40m    [0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m   [0;97;40m▀[0;97;47m▄[0;37;40m▄  [0;97;40m█[0;97;47m▄[0;97;40m▄▄▄[0;37;40m▄[0;90;40m▄[0;37;40m          [0;97;40m█[0;37;40m█ [0;34;40m▒▓▒[0;37;40m [0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m      [0;97;40m█[0;37;40m█ [0;96;40m▌[0;37;40m  [0;97;40m█[0;97;47m▄▄[0;97;40m▄▄[0;37;40m▄[0;90;40m▄[0;37;40m      [0;97;40m█[0;37;40m█   [0;97;40m█[0;97;47m▄[0;97;40m▄▀[0;37;40m   [0;97;40m▄█[0;37;40m██[0;90;40m█[0;37;40m [0m
                [0;97;40m█[0;37;40m█ [0;94;40m▓[0;94;44m▓▒[0;37;40m [0;97;40m█[0;97;47m▄▄[0;97;40m█[0;37;40m█ [0;94;40m▓[0;94;44m▓▒[0;37;40m [0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m  [0;97;40m█[0;97;47m [0;37;40m [0;94;40m▄[0;37;40m [0;97;40m█[0;97;47m [0;97;40m▐▌[0;94;40m▄[0;37;40m  [0;94;40m▄[0;37;40m [0;97;40m█[0;37;40m█[0;90;47m█[0;37;40m    [0;97;40m█[0;37;40m█[0;34;40m█[0;94;44m░░[0;34;40m█[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;44m▒░[0;34;40m█▓[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;44m▒░[0;34;40m█▓[0;97;40m█[0;37;40m██[0;90;40m█[0;97;40m█[0;37;40m█    [0;97;40m▀▀▀▀[0;37;40m   [0;97;40m▄[0;97;47m▀[0;37;40m█[0;90;47m▄[0;90;40m▀[0;37;40m      [0;97;40m▀[0;97;47m▄[0;97;40m▄[0;37;40m▄    [0;97;40m▀[0;97;47m▄[0;37;40m█▄        [0;97;40m█[0;37;40m█[0;34;40m██[0;94;44m░[0;94;40m▄▄[0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m      [0;97;40m█[0;37;40m█   [0;94;40m▄▄■[0;37;40m  [0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m     [0;97;40m█[0;37;40m█ [0;34;40m▓▒░[0;37;40m [0;34;40m░░[0;37;40m [0;97;40m▄█[0;97;47m▀[0;37;40m█[0;90;47m▄[0;37;40m   [0m
                [0;97;40m█[0;37;40m█ [0;94;44m▒░[0;34;40m█[0;37;40m       [0;94;44m▒░[0;34;40m█[0;37;40m [0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m  [0;97;40m█[0;97;47m [0;94;40m▐[0;94;44m▒[0;97;40m▐[0;97;47m▌▄[0;97;40m▄[0;97;47m▀[0;37;40m▄[0;94;40m▀▄[0;94;44m▒[0;94;40m▄[0;37;40m [0;97;40m█[0;37;40m█[0;90;47m█[0;37;40m   [0;97;40m█[0;37;40m█[0;94;44m░░▒▓[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;40m██[0;34;40m██[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;40m██[0;34;40m██[0;97;40m█[0;97;47m [0;37;40m█[0;90;40m█[0;97;40m█[0;37;40m█  [0;34;40m▄[0;94;40m▄[0;97;40m▄▄▄▄▄[0;97;47m▀▀[0;37;40m█[0;90;47m▄[0;90;40m▀[0;37;40m           [0;97;40m▀▀▀[0;97;47m▄[0;37;40m [0;94;40m█[0;97;46m▀[0;97;40m█[0;37;40m█[0;90;47m▀[0;90;40m▄[0;37;40m      [0;97;40m█[0;37;40m█[0;94;44m▒▓[0;94;40m███[0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m      [0;97;40m█[0;37;40m█ [0;94;40m▄██[0;97;40m▐█[0;97;47m▀[0;97;40m▀▀[0;37;40m▀[0;90;40m▀[0;37;40m     [0;97;40m▐[0;97;47m▌[0;37;40m▌[0;34;40m█[0;94;40m▄▌[0;97;40m█[0;97;46m▀[0;97;40m▄[0;34;40m▒[0;94;44m░[0;94;40m█[0;97;46m▀[0;97;40m█[0;37;40m█[0;90;47m▀[0;90;40m▄[0;37;40m  [0m
                [0;97;40m█[0;37;40m█ [0;34;40m██▒[0;37;40m [0;97;40m█[0;97;47m▀▀[0;97;40m█[0;37;40m█ [0;34;40m██▒[0;37;40m [0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m [0;97;40m▐[0;97;47m▌[0;37;40m▌[0;94;44m▒░[0;34;40m▄[0;97;40m▀[0;37;40m█▀▀[0;34;40m▄[0;94;44m░▒░▒░[0;37;40m [0;97;40m█[0;37;40m█[0;90;47m█[0;37;40m  [0;97;41m█[0;37;40m█[0;94;44m░░▒▓[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;40m█▓[0;94;44m▒[0;34;40m█[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;40m█▓[0;94;44m▒[0;34;40m█[0;97;40m█[0;37;40m██[0;90;40m█[0;97;40m█[0;37;40m█ [0;94;44m░▒▓[0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m           [0;97;40m▐█▀▀▀▀█[0;97;47m▄▀[0;37;40m▀ [0;94;40m█▓[0;94;44m▒[0;97;40m█[0;37;40m█[0;90;47m▐[0;90;40m▌[0;37;40m     [0;97;40m█[0;37;40m█[0;94;44m░[0;94;40m░[0;94;44m▒▓[0;94;40m█[0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m      [0;97;40m█[0;37;40m█ [0;94;40m░[0;94;44m▒▓[0;34;40m▌[0;97;40m▀[0;97;47m▄▄[0;97;40m▀▀[0;97;47m▄[0;37;40m█[0;90;47m▀[0;90;40m▄[0;37;40m  [0;97;40m▐[0;97;47m▌[0;37;40m▌[0;94;44m░▓▒[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;44m▒[0;94;40m█▓[0;94;44m▒[0;97;40m█[0;37;40m█[0;90;47m▐[0;90;40m▌[0;37;40m [0m
                [0;97;40m█[0;37;40m█ [0;34;40m░░░[0;37;40m [0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;37;40m█ [0;34;40m░░░[0;37;40m [0;97;40m█[0;37;40m██[0;90;40m█[0;97;40m▄[0;97;47m▀[0;37;40m█ [0;34;40m▒░▒█[0;37;40m [0;34;40m▀▀[0;97;40m▄▄[0;97;47m▀[0;37;40m▄[0;34;40m▀█▒[0;37;40m [0;97;40m▀[0;97;47m▄[0;90;47m▀[0;90;40m▄[0;97;41m█[0;37;40m█[0;94;44m░▒▓▒[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;44m█▓▒░[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;44m█▓▒░[0;97;40m█[0;37;40m██[0;90;40m█[0;97;40m█[0;37;40m█ [0;34;40m█[0;94;44m▓▒[0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m           [0;97;40m█[0;37;40m█ [0;94;44m▓▒░[0;34;40m▄▄[0;37;40m [0;34;40m▓[0;94;44m░▒░[0;37;40m [0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m     [0;97;40m█[0;37;40m█[0;94;44m▒░▒[0;94;40m▒[0;94;44m▒[0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m      [0;97;40m▐[0;97;47m▌[0;37;40m▌ [0;94;40m░▒[0;94;44m▒░[0;34;40m█▓▒░[0;97;40m▀[0;97;47m▄[0;37;40m█[0;90;47m▀[0;90;40m▄[0;37;40m [0;97;40m█[0;37;40m█[0;94;44m░▓▒░[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;44m░▒░░[0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m [0m
                [0;97;40m█[0;97;47m [0;37;40m [0;34;40m░░░[0;37;40m [0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;97;47m [0;37;40m [0;34;40m░░░[0;37;40m [0;97;40m█[0;37;40m██[0;90;40m█[0;97;40m█[0;97;47m [0;37;40m [0;34;40m░░[0;37;40m [0;97;40m▄▄[0;97;47m▀▀▀[0;37;40m█[0;90;47m▄[0;97;47m▀[0;97;40m▄[0;37;40m [0;34;40m▒░[0;97;40m▄[0;97;47m▀ [0;90;47m▄[0;90;40m▀[0;97;41m█[0;37;40m█[0;94;44m░▒▓▓[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;44m▒░[0;34;40m█░[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;94;44m▒░[0;34;40m█░[0;97;40m█[0;37;40m██[0;90;40m█[0;97;40m█[0;37;40m█   [0;34;40m█[0;97;40m█[0;37;40m██[0;90;40m█[0;37;40m          [0;97;40m▄[0;97;47m▀[0;37;40m▀[0;34;40m▄[0;94;44m░[0;34;40m█▓▒░[0;37;40m [0;34;40m▒░▓▀[0;97;40m▄[0;97;47m▀ [0;90;47m▄[0;90;40m▀[0;37;40m     [0;97;40m█[0;37;40m█[0;94;44m░░░▒░[0;97;40m█[0;97;47m  [0;90;40m█[0;37;40m       [0;97;40m▀[0;97;47m▄[0;37;40m▄ [0;34;40m▀[0;94;44m░[0;34;40m██▓▒░[0;37;40m [0;97;40m█[0;97;47m  [0;90;40m█[0;97;40m▄[0;97;47m▀[0;37;40m▀[0;34;40m▄[0;94;44m░[0;34;40m█▓[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;34;40m░▓▀[0;97;40m▄[0;97;47m▀ [0;90;47m▄[0;90;40m▀[0;37;40m [0m
                [0;97;40m█[0;97;47m▄[0;97;40m▄▄▄▄▄█[0;37;40m█[0;90;40m█[0;97;40m█[0;97;47m▄[0;97;40m▄▄▄▄▄█[0;37;40m██[0;90;40m█[0;97;40m█[0;97;47m▄[0;97;40m▄▄▄[0;97;47m▀[0;37;40m█[0;90;47m▄▄[0;90;40m▀▀▀[0;37;40m   [0;97;40m▀▄[0;97;47m▀ [0;90;47m▄[0;90;40m▀[0;37;40m  [0;97;41m█[0;97;47m▄[0;97;44m▄▄▄▄[0;97;40m█[0;37;40m█[0;90;40m█[0;97;40m█[0;97;44m▄▄▄[0;97;40m▄█[0;37;40m█[0;90;40m█[0;97;40m█[0;97;44m▄▄▄[0;97;40m▄█[0;37;40m██[0;90;40m█[0;97;40m█[0;97;47m▄[0;97;40m▄▄▄▄█[0;37;40m█▀           [0;97;40m█[0;97;47m▄[0;97;40m▄▄▄▄▄▄▄▄▄▄[0;97;47m▀▀[0;90;47m▄[0;37;40m▀[0;90;40m▀[0;37;40m       [0;97;40m█[0;97;47m▄[0;97;40m▄▄▄▄▄█[0;37;40m██[0;90;40m█[0;37;40m         [0;97;40m▀▀[0;97;47m▄[0;97;40m▄▄▄▄▄[0;97;47m▀▀[0;90;47m▄[0;37;40m▀[0;90;40m▀[0;37;40m [0;97;40m█[0;97;47m▄[0;97;40m▄▄▄▄▄▄█[0;37;40m█[0;97;40m█▄[0;97;47m▀▀[0;90;47m▄[0;37;40m▀[0;90;40m▀[0;37;40m   [0m""";

        System.out.println(LINE);
        System.out.println(banner);
        System.out.print(LINE);
        System.out.println("\n\tHeh Heh Wasup broh I'm Hampster.");
        System.out.println("\tWhaddya want?");
        System.out.println(LINE);

        
        List<Task> tasks;
        
        try {
            tasks = Savefile.load();
        } catch (IOException e) {
            tasks = new ArrayList<>();
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().trim();

            System.out.println(LINE);

            try {
                if (userInput.isBlank()) {
                    throw new HampsterException("Broh... you didn't say anything.");
                }

                String[] parts = userInput.trim().split("\\s+");
                Command command = parseCommand(parts[0]);

                switch (command) {
                    case BYE:
                        scanner.close();
                        System.out.println("\tVerabschiedung");
                        System.out.println(LINE);
                        return;

                    case LIST:
                        handleList(tasks);
                        break;
                    case MARK:
                        handleMark(parts, tasks, true);
                        break;
                    case UNMARK:
                        handleMark(parts, tasks, false);
                        break;
                    case DELETE:
                        handleDelete(parts, tasks);
                        break;
                    case TODO:
                        handleTodo(parts, tasks);
                        break;
                    case DEADLINE:
                        handleDeadline(parts, tasks);
                        break;
                    case EVENT:
                        handleEvent(parts, tasks);
                        break;
                }

                Savefile.save(tasks);

            } catch (HampsterException e) {
                System.out.println("\tOOPS!!! " + e.getMessage());
            }
            System.out.println(LINE);
        }
    }

    private static Command parseCommand(String word) throws HampsterException {
        try {
            return Command.valueOf(word.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new HampsterException(
                    "Broh... I got no clue what that means.");
        }
    }

    private static void handleList(List<Task> tasks) {
        System.out.println("\tListing your tasks broh");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i));
        }
    }

    private static void handleMark(String[] parts, List<Task> tasks, boolean done)
            throws HampsterException {
        if (parts.length != 2) {
            throw new HampsterException(
                    "Broh... gimme exactly one task number. Try: "
                            + (done ? "mark" : "unmark") + " <task number>");
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new HampsterException(
                    "Broh... '" + parts[1] + "' ain't a task number.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new HampsterException(
                    "Broh... task " + taskNumber + " doesn't exist.");
        }

        Task task = tasks.get(taskNumber - 1);

        if (done) {
            task.mark();
            System.out.println("\tBoom. Task " + taskNumber + " is donezo.");
        } else {
            task.unmark();
            System.out.println("\tAight. Task " + taskNumber + " is back in action.");
        }

        System.out.println("\t" + task);
    }

    private static void handleDelete(String[] parts, List<Task> tasks)
            throws HampsterException {
        if (parts.length != 2) {
            throw new HampsterException(
                    "Broh... gimme exactly one task number. Try: delete <task number>");
        }

        int taskNumber;

        try {
            taskNumber = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new HampsterException(
                    "Broh... '" + parts[1] + "' ain't a task number.");
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new HampsterException(
                    "Broh... task " + taskNumber + " doesn't exist.");
        }

        Task removedTask = tasks.remove(taskNumber - 1);

        System.out.println("\tNoted broh. I've removed this task:");
        System.out.println("\t  " + removedTask);
        System.out.println("\tNow you've got " + tasks.size() + " tasks in the list.");
    }

    private static void handleTodo(String[] parts, List<Task> tasks)
            throws HampsterException {
        String description = Arrays.stream(parts)
                .skip(1)
                .collect(Collectors.joining(" "))
                .trim();

        if (description.isEmpty()) {
            throw new HampsterException(
                    "Broh... you gotta tell me what the todo actually is.");
        }

        tasks.add(new ToDo(false, description));

        System.out.println("\tAight, added that todo broh.");
        System.out.println("\t" + tasks.get(tasks.size() - 1));
        System.out.println("\tYou've got " + tasks.size() + " tasks now.");
    }

    private static void handleDeadline(String[] parts, List<Task> tasks)
            throws HampsterException {
        String input = Arrays.stream(parts)
                .skip(1)
                .collect(Collectors.joining(" "))
                .trim();

        if (input.isEmpty()) {
            throw new HampsterException(
                    "Broh... a deadline needs a description.");
        }

        String[] deadlineParts = input.split("\\s+/by\\s+", 2);

        if (deadlineParts.length != 2) {
            throw new HampsterException(
                    "Whoa there broh, deadlines need a /by. "
                            + "Try: deadline <description> /by <date or time>");
        }

        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();

        if (description.isEmpty()) {
            throw new HampsterException(
                    "Broh... what are you actually trying to get done?");
        }

        if (by.isEmpty()) {
            throw new HampsterException(
                    "Broh... you forgot when this thing is due.");
        }

        tasks.add(new Deadline(false, description, by));

        System.out.println("\tDeadline locked in.");
        System.out.println("\t" + tasks.get(tasks.size() - 1));
        System.out.println("\tYou've got " + tasks.size() + " tasks now.");
    }

    private static void handleEvent(String[] parts, List<Task> tasks)
            throws HampsterException {
        String input = Arrays.stream(parts)
                .skip(1)
                .collect(Collectors.joining(" "))
                .trim();

        if (input.isEmpty()) {
            throw new HampsterException(
                    "Broh... an event needs a description.");
        }

        String[] eventParts = input.split("\\s+/from\\s+", 2);

        if (eventParts.length != 2) {
            throw new HampsterException(
                    "Broh, events need a /from time. "
                            + "Try: event <description> /from <start> /to <end>");
        }

        String description = eventParts[0].trim();
        String[] timeParts = eventParts[1].split("\\s+/to\\s+", 2);

        if (timeParts.length != 2) {
            throw new HampsterException(
                    "Broh, where does this event end? "
                            + "Try: event <description> /from <start> /to <end>");
        }

        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (description.isEmpty()) {
            throw new HampsterException(
                    "Broh... tell me what the event actually is.");
        }

        if (from.isEmpty()) {
            throw new HampsterException(
                    "Broh... you forgot when the event starts.");
        }

        if (to.isEmpty()) {
            throw new HampsterException(
                    "Broh... you forgot when the event ends.");
        }

        tasks.add(new Event(false, description, from, to));

        System.out.println("\tEvent secured broh.");
        System.out.println("\t" + tasks.get(tasks.size() - 1));
        System.out.println("\tYou've got " + tasks.size() + " tasks now.");
    }
}
