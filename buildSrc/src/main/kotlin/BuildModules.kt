/**
 * Configuration of build modules
 */
object BuildModules {
    const val APP = ":app"

    object Provide {
        const val APIS = ":provide:apis"
        const val DATASOURCES = ":provide:datasources"
        const val REPOSITORIES = ":provide:repositories"
        const val SYSTEMS = ":provide:systems"

        object Mocks {
            const val APIS = ":provide:mocks:apis"
        }
    }

    object Features {
        const val GITHUB_CLIENT = ":features:githubclient"
    }

    object Core {
        const val APIS = ":core:apis"
        const val DATASOURCES = ":core:datasources"
        const val ENTITIES = ":core:entities"
        const val REPOSITORIES = ":core:repositories"
        const val SYSTEMS = ":core:systems"
        const val USECASES = ":core:usecases"
    }

    object Common {
        const val RESOURCES = ":common:resources"
        const val DEPENDENCY_INJECTION = ":common:di"
    }
}
