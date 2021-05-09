package hu.dmorvai.myseries.utils

import java.util.concurrent.Executor

class UiExecutor : Executor {
    override fun execute(command: Runnable) {
        command.run()
    }
}
