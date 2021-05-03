class CommandLineArgsParser {
    companion object {
        fun parse(argument: String): MatchResult.Destructured {
            return Regex("^--(\\w+)=(.+)$")
                .find(argument)!!
                .destructured
        }
    }
}