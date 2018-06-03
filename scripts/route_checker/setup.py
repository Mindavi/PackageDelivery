from cx_Freeze import setup, Executable

base = None

executables = [Executable("routechecker.py", base=base)]

packages = ["idna", "os", "sys", "csv"]
options = {
    'build_exe': {
        'packages': packages,
    },
}

setup(
    name="Routechecker",
    options=options,
    version="1",
    description='Check a route csv file for validity',
    executables=executables
)
